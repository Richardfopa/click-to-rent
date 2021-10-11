package com.orange.click_2_rent.Firebase;

import static com.orange.click_2_rent.Models.FirebasesUtil.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Photo;
import com.orange.click_2_rent.Models.Service;
import com.orange.click_2_rent.Models.Users;
import com.orange.click_2_rent.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Storage {


    public static void uploadImageViewToStorage(@NonNull ImageView imageview, @NonNull StorageReference photoRef, Users users){
        imageview.setDrawingCacheEnabled(true);
        imageview.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("STORAGE","FAILLURE INSERTION");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("STORAGE","INSERTION REUSSI");
            }
        });

        // image imagedownload uri from firebase

        Task<Uri> urlTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return photoRef.getDownloadUrl();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri photoservice = task.getResult();
                            Log.d("FILEURL", "onComplete: "+ photoservice.toString());

                            users.setPhotoClient(new Photo(photoservice.toString(),photoservice.toString(),photoservice.toString()));

                            Log.d("STORAGE","RECUPERATION DU SERVICE");
                            FirebasesUtil.addUsers(users);
                            Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");


                        } else {
                            // Handle failures
                            // ...
                        }
                    }
                });

    }

    public static void uploadImageUserToStorage(Context context, @NonNull ImageView imageview, @NonNull StorageReference photoRef, Users users){
        imageview.setDrawingCacheEnabled(true);
        imageview.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("STORAGE","FAILLURE INSERTION");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("STORAGE","INSERTION REUSSI");
            }
        });

        // image imagedownload uri from firebase

        Task<Uri> urlTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return photoRef.getDownloadUrl();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri photoservice = task.getResult();
                            Log.d("FILEURL", "onComplete: "+ photoservice.toString());

                            users.setPhotoClient(new Photo(users.getId(),photoservice.toString(),photoservice.toString()));
                            users.setPhoto_user(photoservice.toString());

                            Log.d("STORAGE","RECUPERATION DU SERVICE");
                            Toast.makeText(context,"Creation de compte reussi ",Toast.LENGTH_LONG).show();

                            FirebasesUtil.addUsers(users);
                            Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");


                        } else {
                            // Handle failures
                            // ...
                        }
                    }
                });

    }
public static void uploadImageServiceToStorage(Context context, @NonNull ImageView imageview, @NonNull StorageReference photoRef, Service service){
        imageview.setDrawingCacheEnabled(true);
        imageview.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("STORAGE","FAILLURE INSERTION");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("STORAGE","INSERTION REUSSI");
            }
        });

        // image imagedownload uri from firebase

        Task<Uri> urlTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return photoRef.getDownloadUrl();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri photoservice = task.getResult();
                            Log.d("FILEURL", "onComplete: "+ photoservice.toString());
                            ArrayList<Photo> photo = new ArrayList<>();
                            photo.add(new Photo(service.getId(),photoservice.toString(),photoservice.toString()));
                            service.setPhotos(photo);
                            service.setPhoto_service(photoservice.toString());

                            Log.d("STORAGE","RECUPERATION DU SERVICE");
                            Toast.makeText(context,"Creation de compte reussi ",Toast.LENGTH_LONG).show();

                            FirebaseFirestore.getInstance().collection("services")
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
                            Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");


                        } else {
                            // Handle failures
                            // ...
                        }
                    }
                });

    }

}
