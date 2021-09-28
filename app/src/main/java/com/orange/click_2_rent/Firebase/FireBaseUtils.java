package com.orange.click_2_rent.Firebase;


import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.orange.click_2_rent.Models.Categorie;
import com.orange.click_2_rent.Models.Commentaire;
import com.orange.click_2_rent.Models.Service;


import java.util.Map;

public class FireBaseUtils {

    public static final String COL_USERS = "users";
    public static final String COL_SERVICES = "services";
    public static final String COL_OPINION = "opinion";
    public static final String COL_CATEGORY = "category";
    public static  final String COL_PHOTO = "photo";
    public static final String COL_ADMIN = "admin";


    static CollectionReference mRefCollection;

    public static CollectionReference getReferenceFirestore(String collectionName){
        if (mRefCollection == null){
            FirebaseFirestore db =  FirebaseFirestore.getInstance();
            mRefCollection = db.collection(collectionName);
            return mRefCollection;
        }
        return mRefCollection;
    }


    public  static  void addService(Service service){
        FireBaseUtils.getReferenceFirestore(COL_SERVICES)
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

    }

    public  static  void addCategory(Categorie cat){
        FireBaseUtils.getReferenceFirestore(COL_CATEGORY)
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
        FireBaseUtils.getReferenceFirestore(COL_OPINION)
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


}

