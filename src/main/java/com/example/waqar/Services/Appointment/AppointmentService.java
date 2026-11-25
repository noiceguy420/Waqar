package com.example.waqar.Services.Appointment;

import com.example.waqar.Dtos.AppointmentDtos.AppointmentSetDto;
import com.example.waqar.Entities.Appointment;
import com.example.waqar.Entities.Patient;
import com.example.waqar.Exceptions.BadRequestException;
import com.example.waqar.Exceptions.ClientPatientMismatchException;
import com.example.waqar.Exceptions.PatientNotFoundException;
import com.example.waqar.Mappers.AppointmentMapper;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Repositories.AppointmentRepo;
import com.example.waqar.Repositories.PatientRepo;
import com.example.waqar.Services.SecurityServices.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppointmentService { //TODO: a method for past appointments
    private final PatientRepo patientRepo;
    private final AuthService authService;
    private final ClientMapper clientMapper;
    private final AppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;

    public AppointmentSetDto getUpcomingPatientAppointments(Integer pid) {
        //validate current user owns requested patient
        if(pid == null)
            throw new BadRequestException("pid is null");
        Patient pat = this.patientRepo.findById(pid).orElseThrow(() ->
                new PatientNotFoundException("id", pid.toString()));
        if(pat.clientOwnsPatient(this.authService.getLoggedInClient()))
            throw new ClientPatientMismatchException();
        //validate whether patient even has any appointments
        if(pat.getAppointments().isEmpty())
            return new AppointmentSetDto(new HashSet<>(),
                    this.clientMapper.toDto(pat.getClient()));
        //get future appointments
        Set<Appointment> futurePatientAppointments = this.appointmentRepo.findFuturePatientAppointments(pat);
        return appointmentMapper.toSetDto(futurePatientAppointments);
    }
}
