
package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MemoApi {

    // 메모 전체 조회
    @GET("api/memos")
    Call<List<Memo>> getMemos();

    // 메모 저장
    @POST("api/memos")
    Call<Memo> saveMemo(@Body Memo memo);

    // 메모 수정
    @PUT("api/memos/{id}")
    Call<Memo> updateMemo(
            @Path("id") Long id,
            @Body Memo memo
    );

    // 메모 삭제
    @DELETE("api/memos/{id}")
    Call<Void> deleteMemo(@Path("id") Long id);
}
