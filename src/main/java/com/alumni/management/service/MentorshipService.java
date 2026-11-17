package com.alumni.management.service;

import com.alumni.management.entity.AlumniProfile;
import com.alumni.management.entity.MentorshipRequest;
import com.alumni.management.entity.StudentProfile;
import com.alumni.management.repository.AlumniProfileRepository;
import com.alumni.management.repository.MentorshipRequestRepository;
import com.alumni.management.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorshipService {

    private final MentorshipRequestRepository mentorshipRequestRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final AlumniProfileRepository alumniProfileRepository;

    public MentorshipService(MentorshipRequestRepository mentorshipRequestRepository,
                             StudentProfileRepository studentProfileRepository,
                             AlumniProfileRepository alumniProfileRepository) {
        this.mentorshipRequestRepository = mentorshipRequestRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.alumniProfileRepository = alumniProfileRepository;
    }

    public List<MentorshipRequest> getAllRequests() {
        return mentorshipRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    public List<AlumniProfile> getAllAlumni() {
        return alumniProfileRepository.findAll();
    }

    public void createRequest(Long studentId, Long alumniId, String message) {
        StudentProfile student = studentProfileRepository.findById(studentId).orElseThrow();
        AlumniProfile alumni = alumniProfileRepository.findById(alumniId).orElseThrow();

        MentorshipRequest request = new MentorshipRequest();
        request.setStudent(student);
        request.setAlumni(alumni);
        request.setMessage(message);
        request.setStatus(MentorshipRequest.Status.PENDING);

        mentorshipRequestRepository.save(request);
    }

    public void updateStatus(Long requestId, MentorshipRequest.Status status) {
        MentorshipRequest request = mentorshipRequestRepository.findById(requestId).orElseThrow();
        request.setStatus(status);
        mentorshipRequestRepository.save(request);
    }
}