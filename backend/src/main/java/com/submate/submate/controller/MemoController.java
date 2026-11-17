
package com.submate.submate.controller;

import com.submate.submate.entity.Memo;
import com.submate.submate.repository.MemoRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 메모 저장
    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        return memoRepository.save(memo);
    }

    // 메모 전체 조회
    @GetMapping
    public List<Memo> getMemos() {
        return memoRepository.findAll();
    }

    // 메모 수정
    @PutMapping("/{id}")
    public Memo updateMemo(
            @PathVariable Long id,
            @RequestBody Memo newMemo) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "메모를 찾을 수 없습니다."
                        ));

        memo.setTitle(newMemo.getTitle());
        memo.setContent(newMemo.getContent());

        return memoRepository.save(memo);
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        if (!memoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        memoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
