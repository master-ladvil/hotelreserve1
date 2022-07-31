#include<jni.h>
#include "HotelReservation.h"
#include<stdio.h>




JNIEXPORT void JNICALL Java_HotelReservation_consoleApp
  (JNIEnv *env, jobject mainobj){
    jclass dbclasss = env -> FindClass("Db");
    jmethodID constid = env -> GetMethodID(dbclasss,"<init>","()V");//getmethodid(class,methodname,returntype)
    jmethodID loginId = env -> GetStaticMethodID(dbclasss,"login","()I");
    jmethodID regID = env -> GetStaticMethodID(dbclasss,"register","()V");
    jmethodID roomID = env -> GetStaticMethodID(dbclasss,"getAvailableRooms","()V");
    jmethodID reserveID = env-> GetStaticMethodID(dbclasss,"bookreservation","()V");
    jobject dbobj = env -> NewObject(dbclasss,constid); 

  
    printf("hello.. welcome to the automated hotel reservation system \n you must choose one to continue \n 1. login \n2.register : \n");
    int choice;
      scanf("%d",&choice);
      if(choice == 1){ 
          //env->CallStaticVoidMethod(dbclasss,rehgID);
           jint jnum = env->CallStaticIntMethod(dbclasss,loginId);
           int num = (int) jnum;
           if(num == 1){
             printf(".....logged in from native.....");
             printf(".....showing rooms.....\n");
             env->CallStaticVoidMethod(dbclasss,roomID);
             printf("\n.....reserving.....\n");
             env->CallStaticVoidMethod(dbclasss,reserveID);
           }else{
             printf("failed to login");
           }
      }
      else if(choice == 2) {
          env->CallStaticVoidMethod(dbclasss,regID);
      }
      else printf("within 2");
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    /*printf("Enter the fuullname : ");
    char fname[100];
    char mobile[10];
    scanf("%s",&fname);
    printf("Enter the mobile : ");
    scanf("%s",&mobile);
    jstring jstrname;
    jstring jstrmobile;
    jstrname = env -> NewStringUTF(fname);
    jstrmobile = env -> NewStringUTF(mobile);*/
    
    
   /* void char* login(){
    
    return name;
  }
      
      char fname[100] = "rootu";
      char mobile[10];
      int choice;
      printf("hello.. welcome to the automated hotel reservation system \n you must choose one to continue \n 1. login \n2.register : ");
      scanf("%d",&choice);
      switch(choice){
        case 1: ; break;
        case 2: registerr(); break;
        default: printf("2 kulla da funda");
      }
      
      
      
      /*scanf("%s",&fname);
      printf("enter your mobile num")
      jstrname = env -> NewStringUTF(fname);
      printf("hi %s.. please wait while the db is checked ")
      jstrname = env -> NewStringUTF(fname);
      return jstrname;
      */
    