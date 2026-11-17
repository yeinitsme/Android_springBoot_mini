
package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etMemoTitle;
    private EditText etMemoContent;
    private Button btnSaveMemo;
    private TextView tvServerResponse;
    private RecyclerView recyclerViewMemos;

    private MemoAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMemoTitle = findViewById(R.id.etMemoTitle);
        etMemoContent = findViewById(R.id.etMemoContent);
        btnSaveMemo = findViewById(R.id.btnSaveMemo);
        tvServerResponse = findViewById(R.id.tvServerResponse);
        recyclerViewMemos = findViewById(R.id.recyclerViewMemos);

        memoAdapter = new MemoAdapter();
        recyclerViewMemos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMemos.setAdapter(memoAdapter);

        btnSaveMemo.setOnClickListener(v -> saveMemo());

        loadMemos();
    }

    private void saveMemo() {
        String title = etMemoTitle.getText().toString().trim();
        String content = etMemoContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "제목과 내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Memo memo = new Memo();
        memo.setTitle(title);
        memo.setContent(content);

        RetrofitClient.getApi().saveMemo(memo).enqueue(new Callback<Memo>() {
            @Override
            public void onResponse(Call<Memo> call, Response<Memo> response) {
                if (response.isSuccessful()) {
                    etMemoTitle.setText("");
                    etMemoContent.setText("");
                    tvServerResponse.setText("메모가 저장되었습니다!");
                    loadMemos();
                } else {
                    tvServerResponse.setText("저장 실패: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Memo> call, Throwable t) {
                tvServerResponse.setText("서버 연결 실패: " + t.getMessage());
            }
        });
    }

    private void loadMemos() {
        RetrofitClient.getApi().getMemos().enqueue(new Callback<List<Memo>>() {
            @Override
            public void onResponse(Call<List<Memo>> call, Response<List<Memo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    memoAdapter.setMemoList(response.body());
                    tvServerResponse.setText("메모 목록을 불러왔습니다.");
                } else {
                    tvServerResponse.setText("목록 불러오기 실패: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Memo>> call, Throwable t) {
                tvServerResponse.setText("서버 연결 실패: " + t.getMessage());
            }
        });
    }
}
