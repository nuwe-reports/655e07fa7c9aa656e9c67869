package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;



    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */
    @Test
    void AppointmentUnitTest(){
        Patient patient=new Patient("John","Stevenson",40,"jhonstevenson@hotmail.com");
        Doctor doctor=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        Room room=new Room("Cardiology");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt= LocalDateTime.parse("08:30 15/01/2024", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("09:30 15/01/2024", formatter);
         a1=new Appointment(patient,doctor,room,startsAt,finishesAt);
        assertNotNull(a1);
        assertNotNull(a1.getStartsAt());
        assertNotNull(a1.getFinishesAt());
        assertNotNull(a1.getDoctor());
        assertNotNull(a1.getPatient());
        assertNotNull(a1.getRoom());
        assertEquals("John",a1.getPatient().getFirstName());
        assertEquals("Stevenson",a1.getPatient().getLastName());
        assertEquals(40,a1.getPatient().getAge());
        assertEquals("jhonstevenson@hotmail.com",a1.getPatient().getEmail());
        assertEquals("Elena",a1.getDoctor().getFirstName());



    }

    @Test
    void OverlapsTest(){
        p1=new Patient("Jonh","Stevenson",40,"jhonstevenson@hotmail.com");
        Patient p2=new Patient("Tom","Anderson",30,"tomanderson@hotmail.com");

        Doctor doctor=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        Doctor doctor2=new Doctor("Kim","Smith",32,"kimsmith@gmail.com");

        Room room=new Room("Cardiology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt= LocalDateTime.parse("08:30 15/01/2024", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("09:30 15/01/2024", formatter);
        Appointment appointment=new Appointment(p1,doctor,room,startsAt,finishesAt);
         a2=new Appointment(p2,doctor2,room,startsAt,finishesAt);
        assertTrue(appointment.overlaps(a2));

        Room room2=new Room("Dermatology");
         a3=new Appointment(p2,doctor2,room2,startsAt,finishesAt);
        assertFalse(appointment.overlaps(a3));

        LocalDateTime startsAt2= LocalDateTime.parse("09:30 15/01/2024", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("10:30 15/01/2024", formatter);
        Appointment a4=new Appointment(p2,doctor2,room,startsAt2,finishesAt2);
        assertFalse(appointment.overlaps(a4));
    }


    @Test
    void PatientUnitTest(){
        p1=new Patient("Jonh","Stevenson",40,"jhonstevenson@hotmail.com");
        assertNotNull(p1);
        assertNotNull(p1.getFirstName());
        assertNotNull(p1.getLastName());
        assertNotNull(p1.getEmail());
        assertEquals(40,p1.getAge());

    }

    @Test
    void DoctorUnitTest(){
        d1=new Doctor("Elena","Smith",36,"elenasmit123@gmail.com");
        assertNotNull(d1);
        assertNotNull(d1.getFirstName());
        assertNotNull(d1.getLastName());
        assertNotNull(d1.getEmail());
        assertEquals(36,d1.getAge());
    }

    @Test
    void RoomUnitTest(){
         r1=new Room("Cardiology");
        assertNotNull(r1);
        assertNotNull(r1.getRoomName());
        assertEquals("Cardiology",r1.getRoomName());

    }
}
