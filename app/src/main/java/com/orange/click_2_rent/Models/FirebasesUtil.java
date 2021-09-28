package com.orange.click_2_rent.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FirebasesUtil {


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
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null) {
            service.setNom_prestataire(currentUser.getDisplayName());
            FirebasesUtil.getReferenceFirestore(COL_SERVICES)
//                    .document(id)
//                    .set(service);
//            UserRepository.addMesServive(service.getName());
                    .add(service)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            final Map<String, Object> addServicerToArrayMap = new HashMap<>();
                            addServicerToArrayMap.put("service", service);

//                            db.collection("REFERENCE").document("mDocumentId")
//                                    .update(addUserToArrayMap);

                            Log.d("message", "DocumentSnapshot written with ID: " + documentReference.getId());
                            UserRepository.addMesServive(documentReference.getId());

                            FirebasesUtil.getReferenceFirestore("user_service")
                                    .document(documentReference.getId())
                                    .update("service", addServicerToArrayMap);
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




}
