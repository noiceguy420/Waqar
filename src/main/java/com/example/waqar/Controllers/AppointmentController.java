package com.example.waqar.Controllers;

import com.example.waqar.Dtos.AppointmentDtos.AppointmentSetDto;
import com.example.waqar.Dtos.AppointmentDtos.BookAppointmentDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Services.Appointment.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @GetMapping("upcoming-patient-appointments/{pid}")
    public ResponseEntity<? extends ResponseBodyDto> getUpcomingPatientAppointments(
            @PathVariable(value = "pid", required = false) Integer pid) {
        AppointmentSetDto appointments = this.appointmentService.getUpcomingPatientAppointments(pid);

        return ResponseEntity.ok(appointments);
    }

    @PostMapping("book-an-appointment")
    public ResponseEntity<? extends ResponseBodyDto> bookAppointment(@Valid BookAppointmentDto req) {
        return null;

    }
}
