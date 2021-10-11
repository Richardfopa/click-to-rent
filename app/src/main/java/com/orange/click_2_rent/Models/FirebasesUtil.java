package com.orange.click_2_rent.Models;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class FirebasesUtil {


    public static final String COL_USERS = "users";
    public static final String COL_SERVICES = "services";
    public static final String COL_OPINION = "opinion";
    public static final String COL_CATEGORY = "category";
    public static  final String COL_PHOTO = "photo";
    public static final String COL_ADMIN = "admin";


    static CollectionReference mRefCollection;
    public static final String TAG = "DATABASE";

    public static CollectionReference getReferenceFirestore(String collectionName){
        if (mRefCollection == null){
            FirebaseFirestore db =  FirebaseFirestore.getInstance();
            mRefCollection = db.collection(collectionName);
            return mRefCollection;
        }
        return mRefCollection;
    }


    public  static  void addService(Service service){

        FirebasesUtil.getReferenceFirestore(COL_SERVICES)
                .add(service)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
        //getReferenceFirestore(COL_SERVICES).add(service);


    }
    public  static  void addUsers(Users users){
        FirebasesUtil.getReferenceFirestore(COL_USERS).document(users.getId())
                .set(users);

    }

    public  static  void addCategory(Categorie cat){
        FirebasesUtil.getReferenceFirestore(COL_CATEGORY)
                .add(cat);

    }

    public  static  void addComment(Commentaire comment){
        FirebasesUtil.getReferenceFirestore(COL_OPINION)
                .add(comment);

    }

    public static void  setService(@NonNull Service service) {

        String Uuid = service.getId();

        FirebasesUtil.getReferenceFirestore(COL_SERVICES)
                .document(Uuid)
                .update("photos", FieldValue.arrayUnion(service.getPhotos().get(0)));
        // [END update_document]

    }
    public static void setUsersidService(@NonNull String serviceid) {


        FirebasesUtil.getReferenceFirestore(COL_USERS)
                .document(serviceid)
                .update("mesServices", FieldValue.arrayUnion(serviceid));
        // [END update_document]

    }
    @NonNull
    public static DocumentReference getUsers(String firebaseUsersId) {

        return FirebasesUtil.getReferenceFirestore(COL_USERS).document(firebaseUsersId);

    }
    @NonNull
    public static DocumentReference getService(String serviceId) {

        return FirebasesUtil.getReferenceFirestore(COL_SERVICES).document(serviceId);

    }
}