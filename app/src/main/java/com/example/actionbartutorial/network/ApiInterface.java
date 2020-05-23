package com.example.actionbartutorial.network;

import com.example.actionbartutorial.model.AllFeatures;
import com.example.actionbartutorial.model.AllTransactions;
import com.example.actionbartutorial.model.Allicon;
import com.example.actionbartutorial.model.Amount;
import com.example.actionbartutorial.model.InfoList;
import com.example.actionbartutorial.model.NotificationList;
import com.example.actionbartutorial.model.UpdateInfo;
import com.example.actionbartutorial.model.AllVersion;
import com.example.actionbartutorial.model.CurDate;
import com.example.actionbartutorial.model.OfferList;
import com.example.actionbartutorial.model.Policies;
import com.example.actionbartutorial.model.UserList;
import com.example.actionbartutorial.model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


  @GET("signin.php")
  Call<UserList> getClientInfo(@Query("username") String username, @Query("password") String password);

  @GET("update.php")
  Call<UpdateInfo> getupdateInfo(@Query("caddress") String caddress, @Query("cemail") String cemail, @Query("cphone") String cphone, @Query("cdob") String cdob, @Query("uNm") String uNm, @Query("upass") String upass);

  @GET("notificationdata.php")
  Call<NotificationList> getNotifications(@Query("cid") String cid);

  @GET("fetchdata.php")
  Call<InfoList> getInfoList(@Query("date") String date, @Query("cid") String cid, @Query("iconno") String iconno);

  @GET("fetchdata2.php")
  Call<InfoList> getInfoList2(@Query("date") String date,@Query("cid") String cid,@Query("iconno") String iconno);

  @GET("search.php")
  Call<OfferList> getSearchOffer(@Query("obody") String obody);

  @GET("transfetch.php")
  Call<AllTransactions> getTransactions(@Query("tblNm") String tblNm, @Query("stdate") String stdate, @Query("endate") String endate, @Query("ename") String ename);

  @GET("fetchamount.php")
  Call<Amount> getAmounts(@Query("tblNm") String tblNm, @Query("stdate") String stdate, @Query("endate") String endate, @Query("ename") String ename);

  @GET("clientinfo.php")
  Call<UserList> updateClientInfo(@Query("username") String username);

  @GET("geticoninfo.php")
  Call<Allicon> getAllIconsById(@Query("icon") String icon);

  @GET("about.php")
  Call<AllFeatures> getAllFeatures(@Query("fid") String fid);

  @GET("privacy.php")
  Call<Policies> getAllPolicies();

  @GET("version.php")
  Call<AllVersion> getAllVersions();

  @GET("getdate.php")
  Call<CurDate> getCurrentDate();

  @GET("getweb.php")
  Call<Website> getWebsitesUrl();










}
