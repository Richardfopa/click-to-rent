package com.orange.click_2_rent;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FieldValue;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Users;

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

    public static void updateTel(Number tel){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("telephone", tel);

        }


    }
    public static void updateEmail(String email){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("email", email);
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
                    .update("adresse", adresse);

        }


    }public static void updatePassWord(String motDePasse){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("motDePasse", motDePasse);
            currentUser.updatePassword(motDePasse);

        }


    }public static void updateVille(String ville){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("ville", ville);

        }


    }public static void addMesServive(String service){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("mesServices", FieldValue.arrayUnion(service));

        }


    }public static void addServicesDemande(String service){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("servicesDemande", FieldValue.arrayUnion(service));

        }


    }public static void addMesCommentaires(String comment){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();
        if (id!=null){
            FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                    .document(id)
                    .update("mesCommentaires", FieldValue.arrayUnion(comment));

        }
    }
}
