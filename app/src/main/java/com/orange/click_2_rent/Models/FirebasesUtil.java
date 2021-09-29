package com.orange.click_2_rent.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebasesUtil {
    static final String COL_USERS = "users";
    static final String COL_SERVICES = "services";
    static final String COL_OPINION = "opinion";
    static final String COL_CATEGORY = "category";
    static  final String COL_PHOTO = "photo";
    static final String COL_ADMIN = "admin";


    static CollectionReference mRefCollection;

    public static CollectionReference getReferenceFirestore(String collectionName){
        if (mRefCollection == null){
            FirebaseFirestore db =  FirebaseFirestore.getInstance();
            mRefCollection = db.collection(collectionName);
            return mRefCollection;
        }
        return mRefCollection;
    }
    public  static  void addUser(Users user){
        FirebasesUtil.getReferenceFirestore(COL_USERS)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                        documentReference.update("id", documentReference.getId());
                        user.setId(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG_FAILURE", "Error writing document", e);
                    }
                });

    }

    public  static  void addService(Service service){
        FirebasesUtil.getReferenceFirestore(COL_SERVICES)
                .add(service)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG_FAILURE", "Error writing document", e);
                    }
                });


//        FirebasesUtil.getReferenceFirestore(COL_SERVICES)
//                .document("service"+service.getId())
//                .set(service)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("message", "DocumentSnapshot written with ID: " + service.getId());
//                    }
//                });


    }

    public  static  void addCategory(Categorie cat){
        FirebasesUtil.getReferenceFirestore(COL_CATEGORY)
                .add(cat)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG_FAILURE", "Error writing document", e);
                    }
                });

    }

    public  static  void addComment(Commentaire comment){
        FirebasesUtil.getReferenceFirestore(COL_OPINION)
                .add(comment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG_FAILURE", "Error writing document", e);
                    }
                });

    }


    public  static  void addPhoto(Photo tof){
        FirebasesUtil.getReferenceFirestore(COL_PHOTO)
                .add(tof)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG_FAILURE", "Error writing document", e);
                    }
                });

    }


}
