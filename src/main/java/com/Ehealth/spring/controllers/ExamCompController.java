package com.Ehealth.spring.controllers;

import com.Ehealth.spring.models.DateCal;
import com.Ehealth.spring.models.ExamComp;
import com.Ehealth.spring.models.Visite;
import com.Ehealth.spring.payload.dtos.ExamRequest;
import com.Ehealth.spring.payload.dtos.ValidateExamRequest;
import com.Ehealth.spring.services.ExamCompService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/examens")
public class ExamCompController {
    private final ExamCompService examCompService;

    public ExamCompController(ExamCompService examCompService) {

        this.examCompService = examCompService;
    }
    @PostMapping
    public ResponseEntity <List<ExamComp>>create(@RequestBody List <ExamRequest> examCompReq){
        System.out.println(examCompReq);
        List<ExamComp> createdExams = new ArrayList<>();
        for(ExamRequest request: examCompReq)
        {
            Long employeeId= request.employeeId;
            Long typeExamId = request.getTypeExamId();
            DateCal dateExam = request.getDateExam();
            List<ExamComp> exams = examCompService.create(Collections.singletonList(employeeId), Collections.singletonList(typeExamId),  dateExam);
            createdExams.addAll(exams);
        }
        return ResponseEntity.ok(createdExams);
    }
    @PutMapping("/validate/{id}")
    public ResponseEntity<ExamComp> validateVisite(@PathVariable long id, @RequestBody ValidateExamRequest req) {



        ExamComp examResponse = examCompService.ValidateExam( id, req);


        return ResponseEntity.ok().body(examResponse);
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
   //@PutMapping("/update/{id}")
   //public ResponseEntity<ExamComp>update(@PathVariable(name="id")Long id,
   //                                      @RequestBody ExamComp examCompreq){
   //    ExamComp examComp = examCompService.update(id,true,examCompreq);
   //    return ResponseEntity.ok(examComp);
   //}
   @GetMapping("/unvalid")
   public ResponseEntity<List<ExamComp>> getunvalid() {
       List<ExamComp> examComps = examCompService.getunvalid(true, false);
       return ResponseEntity.ok(examComps);
   }
    @PatchMapping("/del/{id}")
    public ResponseEntity<ExamComp>delete(@PathVariable(name="id")Long id){
        ExamComp examComp = examCompService.delete(id,true);
        return ResponseEntity.ok(examComp);
    }

}
