package com.orange.click_2_rent.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FieldValue;

public class UserRepository {

    public static void addUser(String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String id = currentUser.getUid();
            String userName = currentUser.getDisplayName();
            String email = currentUser.getEmail();

            Users user = new Users();
            user.setId(id);
            user.setNom(userName);
            user.setMotDePasse(password);
            user.setEmail(email);

            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .set(user);
        }

    }

    public static void updateTel(int tel){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("telephone", tel)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "Telephone successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating Telephone", e);
                        }
                    });


        }


    }
    public static void updateEmail(String email){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("email", email)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "email successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating email", e);
                        }
                    });
            currentUser.updateEmail(email);

        }


    }
    public static void updateNamePhoto(String username){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("nom", username);

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                    .build();

            currentUser.updateProfile(profileUpdates);

        }


    }
    public static void updateAdresse(String adresse){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("adresse", adresse)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "adresse successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating adresse", e);
                        }
                    });

        }


    }public static void updatePassWord(String motDePasse){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("motDePasse", motDePasse)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "Password successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating Password", e);
                        }
                    });
            currentUser.updatePassword(motDePasse);

        }


    }public static void updateVille(String ville){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("ville", ville)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "Ville successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating ville", e);
                        }
                    });

        }


    }public static void addMesServive(String idService){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("mesServices", FieldValue.arrayUnion(idService))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "mesService successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating mesService", e);
                        }
                    });

        }


    }public static void addServicesDemande(String idService){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("servicesDemande", FieldValue.arrayUnion(idService))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "serviceDemande successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating serviceDemande", e);
                        }
                    });

        }


    }public static void addMesCommentaires(String comment){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("mesCommentaires", FieldValue.arrayUnion(comment))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.w("SUCCESS", "mesCommentaire successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("NOT SUCCESS", "Error during updating mesCommentaires", e);
                        }
                    });


        }


    }

}
