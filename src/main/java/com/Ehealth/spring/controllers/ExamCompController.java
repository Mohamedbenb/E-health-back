package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.ExamComp;
import com.Ehealth.spring.services.ExamCompService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examens")
public class ExamCompController {
    private final ExamCompService examCompService;

    public ExamCompController(ExamCompService examCompService) {

        this.examCompService = examCompService;
    }
    @PostMapping
    public ResponseEntity<ExamComp> create(@RequestBody ExamComp examCompReq){
        ExamComp examComp = examCompService.create(examCompReq);
        return ResponseEntity.ok(examComp);
    }
    @GetMapping
    public ResponseEntity<List<ExamComp>> getall(){
        List<ExamComp> examComps = examCompService.getall(true);
        return ResponseEntity.ok(examComps);
    }
    @GetMapping("/{empId}")
    public ResponseEntity<List<ExamComp>> getByEmployee(@PathVariable(name = "empId")Long empId){
        List<ExamComp> examComps = examCompService.getbyemployee(empId, true);
        return  ResponseEntity.ok(examComps);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ExamComp>update(@PathVariable(name="id")Long id,
                                          @RequestBody ExamComp examCompreq){
        ExamComp examComp = examCompService.update(id,true,examCompreq);
        return ResponseEntity.ok(examComp);
    }
    @PatchMapping("/del/{id}")
    public ResponseEntity<ExamComp>delete(@PathVariable(name="id")Long id){
        ExamComp examComp = examCompService.delete(id,true);
        return ResponseEntity.ok(examComp);
    }

}
