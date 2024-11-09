package com.example.smartlivingcommunity.data.repository;

import android.net.Uri;

import com.example.smartlivingcommunity.data.model.BulletinBoardModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class BulletinBoardRepository {

    private final FirebaseFirestore db;
    private final CollectionReference noticesRef;
    private final FirebaseStorage storage;
    private static final String COLLECTION_NAME = "bulletinBoard";
    private static final String STORAGE_PATH = "bulletin_attachments";

    public BulletinBoardRepository() {
        db = FirebaseFirestore.getInstance();
        noticesRef = db.collection(COLLECTION_NAME);
        storage = FirebaseStorage.getInstance();
    }

    /**
     * Add a new notice to Firestore.
     *
     * @param notice The BulletinBoardModel object containing notice data.
     * @return Task<DocumentReference> that completes when the notice is added.
     */
    public Task<DocumentReference> addNotice(BulletinBoardModel notice) {
        return noticesRef.add(notice);
    }

    /**
     * Add a new notice with an attachment.
     *
     * @param notice The BulletinBoardModel object containing notice data.
     * @param attachmentUri The URI of the attachment to upload.
     * @return Task<DocumentReference> that completes when the notice and attachment are added.
     */
    public Task<DocumentReference> addNoticeWithAttachment(BulletinBoardModel notice, Uri attachmentUri) {
        StorageReference attachmentRef = storage.getReference()
                .child(STORAGE_PATH)
                .child(new Date().getTime() + "_" + attachmentUri.getLastPathSegment());

        return attachmentRef.putFile(attachmentUri)
                .continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return attachmentRef.getDownloadUrl();
                })
                .continueWithTask(task -> {
                    if (task.isSuccessful()) {
                        notice.setAttachment(task.getResult().toString());
                    }
                    return noticesRef.add(notice);
                });
    }

    /**
     * Retrieve all notices ordered by date in descending order.
     *
     * @return Task<QuerySnapshot> containing the query result.
     */
    public Task<QuerySnapshot> getAllNotices() {
        return noticesRef.orderBy("date", Query.Direction.DESCENDING).get();
    }

    /**
     * Retrieve notices filtered by category.
     *
     * @param category The category to filter notices.
     * @return Task<QuerySnapshot> containing the query result.
     */
    public Task<QuerySnapshot> getNoticesByCategory(String category) {
        return noticesRef.whereEqualTo("category", category)
                .orderBy("date", Query.Direction.DESCENDING)
                .get();
    }

    /**
     * Update an existing notice.
     *
     * @param noticeId The ID of the notice to update.
     * @param updatedNotice The updated BulletinBoardModel object.
     * @return Task<Void> that completes when the notice is updated.
     */
    public Task<Void> updateNotice(String noticeId, BulletinBoardModel updatedNotice) {
        return noticesRef.document(noticeId).set(updatedNotice);
    }

    /**
     * Delete a notice by ID.
     *
     * @param noticeId The ID of the notice to delete.
     * @return Task<Void> that completes when the notice is deleted.
     */
    public Task<Void> deleteNotice(String noticeId) {
        return noticesRef.document(noticeId).delete();
    }

    /**
     * Delete a notice and its associated attachment.
     *
     * @param noticeId The ID of the notice to delete.
     * @param attachmentUrl The URL of the attachment to delete.
     * @return Task<Void> that completes when the notice and attachment are deleted.
     */
    public Task<Void> deleteNoticeWithAttachment(String noticeId, String attachmentUrl) {
        if (attachmentUrl != null && !attachmentUrl.isEmpty()) {
            StorageReference attachmentRef = storage.getReferenceFromUrl(attachmentUrl);
            return attachmentRef.delete()
                    .continueWithTask(task -> noticesRef.document(noticeId).delete());
        } else {
            return deleteNotice(noticeId);
        }
    }

    /**
     * Retrieve recent notices from the last 7 days.
     *
     * @return Task<QuerySnapshot> containing the query result.
     */
    public Task<QuerySnapshot> getRecentNotices() {
        Date sevenDaysAgo = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        return noticesRef.whereGreaterThan("date", sevenDaysAgo)
                .orderBy("date", Query.Direction.DESCENDING)
                .get();
    }

    /**
     * Search notices by title.
     *
     * @param searchTerm The title search term.
     * @return Task<QuerySnapshot> containing the query result.
     */
    public Task<QuerySnapshot> searchNotices(String searchTerm) {
        return noticesRef.orderBy("title")
                .startAt(searchTerm)
                .endAt(searchTerm + "\uf8ff")
                .get();
    }
}
