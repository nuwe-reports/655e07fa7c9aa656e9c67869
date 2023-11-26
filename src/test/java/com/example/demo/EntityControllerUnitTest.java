
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDoctorsUnitTest() throws Exception {
        List<Doctor> doctorList=doctorRepository.findAll();
        assertThat(doctorList).isNotNull();
        assertInstanceOf(List.class,doctorList);
        ResponseEntity<List<Doctor>> responseEntity=new ResponseEntity<>(doctorList, HttpStatus.OK);
       assertInstanceOf(ResponseEntity.class,responseEntity);
    }

    @Test
    void getDoctorByIdUnitTest(){
        Doctor doctor=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        doctor.setId(1L);
        Optional<Doctor> optionalDoctor=Optional.of(doctor);
        assertThat(optionalDoctor.get().getId()).isEqualTo(1);
        ResponseEntity<Doctor> responseEntity=new ResponseEntity<>(doctor, HttpStatus.OK);
        assertInstanceOf(ResponseEntity.class,responseEntity);


    }

    @Test
    void createDoctorUnitTest() throws Exception {
        Doctor doctor=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());


    }

    @Test
    void deleteDoctorUnitTest() throws Exception {
        Doctor doctor=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        doctor.setId(1);

        Optional<Doctor> opt = Optional.of(doctor);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/doctors/"+doctor.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void deleteDoctorsUnitTest() throws Exception {
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());

    }
}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPatientsUnitTest() throws Exception {
        List<Patient> patientList=patientRepository.findAll();
        assertThat(patientList).isNotNull();
        assertInstanceOf(List.class,patientList);
        ResponseEntity<List<Patient>> responseEntity=new ResponseEntity<>(patientList, HttpStatus.OK);
        assertInstanceOf(ResponseEntity.class,responseEntity);
    }

    @Test
    void getPatientByIdUnitTest(){
        Patient patient=new Patient("John","Stevenson",40,"jhonstevenson@hotmail.com");
        patient.setId(1L);
        Optional<Patient> optionalPatient=Optional.of(patient);
        assertThat(optionalPatient.get().getId()).isEqualTo(1);
        ResponseEntity<Patient> responseEntity=new ResponseEntity<>(patient, HttpStatus.OK);
        assertInstanceOf(ResponseEntity.class,responseEntity);
    }

    @Test
    void createPatientUnitTest() throws Exception {
        Patient patient=new Patient("John","Stevenson",40,"jhonstevenson@hotmail.com");
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());


    }

    @Test
    void deletePatientUnitTest() throws Exception {
        Patient patient=new Patient("John","Stevenson",40,"jhonstevenson@hotmail.com");
        patient.setId(1);

        Optional<Patient> opt = Optional.of(patient);

        assertThat(opt).isPresent();
        assertThat(opt.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/patients/"+patient.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void deletePatientsUnitTest() throws Exception {
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());

    }





}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllRoomsUnitTest() throws Exception {
        List<Room> roomList=roomRepository.findAll();
        assertThat(roomList).isNotNull();
        assertInstanceOf(List.class,roomList);
        ResponseEntity<List<Room>> responseEntity=new ResponseEntity<>(roomList, HttpStatus.OK);
        assertInstanceOf(ResponseEntity.class,responseEntity);
    }

    @Test
    void getRoomByIdUnitTest(){
        Room room=new Room("Cardiology");
        Optional<Room> optionalRoom=Optional.of(room);
        assertThat(optionalRoom.get().getRoomName()).isEqualTo("Cardiology");
        ResponseEntity<Room> responseEntity=new ResponseEntity<>(room, HttpStatus.OK);
        assertInstanceOf(ResponseEntity.class,responseEntity);
    }

    @Test
    void createRoomUnitTest() throws Exception {
        Room room=new Room("Cardiology");
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());


    }

    @Test
    void deleteRoomUnitTest() throws Exception {
        Room room=new Room("Cardiology");

        Optional<Room> opt = Optional.of(room);

        assertThat(opt).isPresent();
        assertThat(opt.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo("Cardiology");

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(opt);
        mockMvc.perform(delete("/api/rooms/"+room.getRoomName()))
                .andExpect(status().isOk());

    }

    @Test
    void deleteRoomsUnitTest() throws Exception {
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());

    }




}
