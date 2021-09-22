package com.orange.click_2_rent.Firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.Client;

import java.util.Map;

public class FireBaseUtils {

    public static CollectionReference mRefCollection;
    public static final String TASK_COLLECTION = "TASCHES";
    public static final String CLIENT_COLLECTION = "users";
    public static final String PRESTATAIRES_COLLECTION = "prestataire";
    public static final String SERVICE_COLLECTION = "service";
    private static Object objet;
    private static Context context;
    private static Map<String, Object> map;


    public void setup() {
        // [START get_firestore_instance]
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        // [END set_firestore_settings]
    }

    public static CollectionReference getReferenceFirestore(String CollectionName){
        if(mRefCollection == null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            mRefCollection = db.collection(CollectionName);
            return mRefCollection;
        }
        return mRefCollection;
    }

    public static void addUser(String collectionName, Map<String, Object> documentName, Context context){
        getReferenceFirestore(collectionName).document()
                .set(documentName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Creation","Creation et insertion reussi");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ECHECS","Insertion echouer ");
                    }
                });
    }

    public static CollectionReference getCollection(String CollectionName){

        CollectionReference cr = getReferenceFirestore(CLIENT_COLLECTION)
                .getFirestore().collection(CollectionName);

        Log.d("STRUCT:",cr.toString());

        return cr;
    }

    public void getAllUsers() {
        // [START get_all_users]
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });
        // [END get_all_users]
    }

    public void getAllDoc(String collectionName) {
        // [START get_all_users]
        getReferenceFirestore(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("ALLDOC", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("ALLDOC", "Error getting documents.", task.getException());
                        }
                    }
                });
        // [END get_all_users]
    }

    public void setDocument(String collectionName, String documentId, Map<String, Object> data){
        getReferenceFirestore(collectionName).document(documentId)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });
        // [END set_document]

    }
    public void updateDocument(String collectionName, String documentId, Map<String, Object> data){
        getReferenceFirestore(collectionName).document(documentId)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });
        // [END set_document]

    }

}
