import React, { useState, useEffect } from 'react';
import Card from './Card';
import Navbar from '../Home/Navbar';
import Footer from '../Home/Footer';
import StarRatings from 'react-star-ratings';
import { Link } from 'react-router-dom';

const Doctor = () => {
  const [doctors, setDoctors] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedDoctor, setSelectedDoctor] = useState(null);
  const [appointmentDate, setAppointmentDate] = useState("");
  const [appointmentTime, setAppointmentTime] = useState("");
  const [reason, setReason] = useState("");
  const [confirmationDetails, setConfirmationDetails] = useState(null);
  const [username, setUsername] = useState('');
  const [rating, setRating] = useState(0);
  const [userDetails, setUserDetails] = useState(null);

  const fetchDoctors = async () => {
    try {
      const storedname = localStorage.getItem('username');
      if (storedname) {
        setUsername(storedname);
      }
      const response = await fetch('http://localhost:8080/doctors/all');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      setDoctors(data);
    } catch (error) {
      console.error('Error fetching doctors:', error);
    }
  };

  const fetchUserDetails = async () => {
    try {
      const response = await fetch(`http://localhost:8080/userdetails/user?username=${username}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      setUserDetails(data[0]); 
    } catch (error) {
      console.error('Error fetching user details:', error);
    }
  };

  useEffect(() => {
    fetchDoctors();
    if (username) {
      fetchUserDetails();
    }
  }, [username]);

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredDoctors = doctors.filter((doctor) => {
    return (
      doctor.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      doctor.specialty.toLowerCase().includes(searchTerm.toLowerCase()) ||
      doctor.hospital.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const handleConfirmAppointment = async () => {
    if (!appointmentDate || !appointmentTime || !reason) {
      alert("Please fill in all fields.");
      return;
    }

    if (!userDetails) {
      alert("Error fetching user details.");
      return;
    }

    const appointmentData = {
      doctorname: selectedDoctor.name,
      doctorEmail: selectedDoctor.email,
      doctorPhone: selectedDoctor.phone,
      doctorSpecialty: selectedDoctor.specialty,
      doctorHospital: selectedDoctor.hospital,
      doctorImageUrl: selectedDoctor.imageUrl,
      doctorusername: selectedDoctor.username,
      appointmentDate: appointmentDate,
      appointmentTime: appointmentTime,
      username: username,
      status: 'pending'
    };

    try {
      const appointmentResponse = await fetch('http://localhost:8080/appointments/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(appointmentData),
      });

      if (!appointmentResponse.ok) {
        throw new Error('Failed to book appointment');
      }
      const appointmentResult = await appointmentResponse.json();
      const appointmentId = appointmentResult.id;

      const patientData = {
        name: `${userDetails.firstName} ${userDetails.lastName}`,
        patientusername: userDetails.username,
        email: userDetails.email,
        phone: userDetails.phone,
        illness: reason,
        doctor: selectedDoctor.username,
        status: 'ongoing',
        appointmentid: appointmentId,
        treatment: ''
      };

      const patientResponse = await fetch('http://localhost:8080/doctor/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(patientData),
      });

      if (patientResponse.ok) {
        setConfirmationDetails(appointmentData);
      } else {
        throw new Error('Failed to post patient details');
      }
    } catch (error) {
      console.error('Error booking appointment:', error);
      alert('Error booking appointment');
    }
  };

  const handleCancelAppointment = () => {
    setSelectedDoctor(null);
    setReason('');
  };

  const handleRatingChange = (newRating) => {
    setRating(newRating);
  };

  if (confirmationDetails) {
    return (
      <div>
        <Navbar />
        <div style={newStyles.container}>
          <div style={newStyles.confirmationCard}>
            <div style={newStyles.cardHeader}>
              <h1 style={newStyles.cardTitle}>Appointment Confirmed</h1>
              <p style={newStyles.cardSubtitle}>Your appointment has been successfully booked!</p>
            </div>
            <div style={newStyles.cardContent}>
              <div style={newStyles.doctorDetails}>
                <img src={confirmationDetails.doctorImageUrl} alt="Doctor" style={newStyles.doctorImage} />
                <div style={newStyles.doctorText}>
                  <h2 style={newStyles.doctorName}>{confirmationDetails.doctorname}</h2>
                  <p style={newStyles.doctorSpecialty}>{confirmationDetails.doctorSpecialty}</p>
                </div>
              </div>
              <div style={newStyles.appointmentInfo}>
                <div style={newStyles.infoGroup}>
                  <h3 style={newStyles.infoLabel}>Date</h3>
                  <p style={newStyles.infoValue}>{confirmationDetails.appointmentDate}</p>
                </div>
                <div style={newStyles.infoGroup}>
                  <h3 style={newStyles.infoLabel}>Time</h3>
                  <p style={newStyles.infoValue}>{confirmationDetails.appointmentTime}</p>
                </div>
                <div style={newStyles.infoGroup}>
                  <h3 style={newStyles.infoLabel}>Location</h3>
                  <p style={newStyles.infoValue}>{confirmationDetails.doctorHospital}</p>
                </div>
              </div>
            </div>
            <div style={newStyles.feedbackContainer}>
              <h2 style={newStyles.feedbackTitle}>Rate Your Experience</h2>
              <StarRatings
                rating={rating}
                starRatedColor="gold"
                starEmptyColor="#ccc"
                starDimension="30px"
                starSpacing="5px"
                changeRating={setRating}
                numberOfStars={5}
                name='rating'
                style={newStyles.starRating}
              />
            </div>
            <div style={newStyles.buttonContainer}>
              <Link to="/appointments" style={newStyles.linkButton}>
                <button style={{ ...newStyles.button, ...newStyles.goToAppointmentsButton }}>Go to Appointments</button>
              </Link>
            </div>
          </div>
        </div>
        <Footer />
      </div>
    );
  }

  return (
    <div>
      <Navbar />
      <div style={{ display: 'flex', justifyContent: 'center', marginTop: '120px' }}>
        <input
          type="text"
          style={newStyles.searchInput}
          placeholder="Search Doctors..."
          value={searchTerm}
          onChange={handleSearch}
        />
      </div>
      <main style={newStyles.mainContent}>
        <div style={newStyles.doctorGrid}>
          {filteredDoctors.map((doctor) => (
            <Card
              key={doctor.id}
              img={doctor.imageUrl}
              title={`${doctor.name}`}
              description={`${doctor.specialty} with ${doctor.yearsOfExperience} years of experience. Consultations at ${doctor.hospital}.`}
              style={newStyles.card}
              onHoverStyle={newStyles.cardHover}
            >
              <button
                style={newStyles.bookButton}
                onClick={() => setSelectedDoctor(doctor)}
              >
                Book Appointment
              </button>
            </Card>
          ))}
        </div>
      </main>

      {selectedDoctor && (
        <div style={newStyles.modal}>
          <h2 style={newStyles.modalTitle}>Book Appointment with {selectedDoctor.name}</h2>
          <div>
            <label style={newStyles.label}>Date:</label>
            <input
              type="date"
              value={appointmentDate}
              onChange={(e) => setAppointmentDate(e.target.value)}
              style={newStyles.input}
            />
            <label style={newStyles.label}>Time:</label>
            <input
              type="time"
              value={appointmentTime}
              onChange={(e) => setAppointmentTime(e.target.value)}
              style={newStyles.input}
            />
            <label style={newStyles.label}>Reason for Booking:</label>
            <textarea
              value={reason}
              onChange={(e) => setReason(e.target.value)}
              style={newStyles.textarea}
            />
          </div>
          <div style={newStyles.buttonContainer}>
            <button
              onClick={handleConfirmAppointment}
              style={{ ...newStyles.button, ...newStyles.confirmButton }}
            >
              Confirm
            </button>
            <button
              onClick={handleCancelAppointment}
              style={{ ...newStyles.button, ...newStyles.cancelButton }}
            >
              Cancel
            </button>
          </div>
        </div>
      )}
      <Footer />
    </div>
  );
};

const newStyles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '110vh',
    background: '#f0f2f5',
  },
  confirmationCard: {
    background: '#fff',
    padding: '50px',
    borderRadius: '10px',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
    textAlign: 'center',
    maxWidth: '800px',
    width: '100%',
  },
  cardHeader: {
    marginBottom: '20px',
  },
  cardTitle: {
    fontSize: '24px',
    fontWeight: 'bold',
    color: '#333',
  },
  cardSubtitle: {
    fontSize: '16px',
    color: '#666',
  },
  cardContent: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginBottom: '20px',
  },
  doctorDetails: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '20px',
  },
  doctorImage: {
    borderRadius: '50%',
    width: '80px',
    height: '80px',
    marginRight: '20px',
  },
  doctorText: {
    textAlign: 'left',
  },
  doctorName: {
    fontSize: '25px',
    fontWeight: 'bold',
    color: '#333',
  },
  doctorSpecialty: {
    fontSize: '16px',
    color: '#666',
  },
  appointmentInfo: {
    width: '100%',
    textAlign: 'right',
  },
  infoGroup: {
    marginBottom: '10px',
  },
  infoLabel: {
    fontSize: '14px',
    fontWeight: 'bold',
    color: '#333',
  },
  infoValue: {
    fontSize: '16px',
    color: '#666',
  },
  feedbackContainer: {
    marginTop: '20px',
  },
  feedbackTitle: {
    fontSize: '18px',
    fontWeight: 'bold',
    color: '#333',
    marginBottom: '10px',
  },
  starRating: {
    display: 'inline-block',
  },
  buttonContainer: {
    marginTop: '20px',
  },
  linkButton: {
    textDecoration: 'none',
  },
  button: {
    padding: '10px 20px',
    borderRadius: '5px',
    border: 'none',
    cursor: 'pointer',
    fontSize: '16px',
    fontWeight: 'bold',
  },
  goToAppointmentsButton: {
    background: '#012441',
    color: '#fff',
  },
  searchInput: {
    padding: '10px',
    fontSize: '18px',
    width: '300px',
    borderRadius: '10px',
    border: '1px solid #ddd',
  },
  mainContent: {
    padding: '20px',
  },
  doctorGrid: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
    gap: '20px',
    padding: '20px',
  },
  card: {
    transition: 'transform 0.3s',
  },
  cardHover: {
    transform: 'scale(1.05)',
  },
  bookButton: {
    background: '#012441',
    color: '#fff',
    padding: '10px 20px',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '16px',
    fontWeight: 'bold',
  },
  modal: {
    position: 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    background: '#fff',
    padding: '20px',
    borderRadius: '10px',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
    zIndex: 1000,
    maxWidth: '400px',
    width: '100%',
  },
  modalTitle: {
    fontSize: '20px',
    fontWeight: 'bold',
    marginBottom: '20px',
  },
  label: {
    display: 'block',
    marginBottom: '10px',
    fontWeight: 'bold',
    color: '#333',
  },
  input: {
    width: '100%',
    padding: '10px',
    marginBottom: '10px',
    borderRadius: '5px',
    border: '1px solid #ddd',
  },
  textarea: {
    width: '100%',
    padding: '10px',
    marginBottom: '10px',
    borderRadius: '5px',
    border: '1px solid #ddd',
    minHeight: '100px',
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'space-between',
    marginTop: '20px',
  },
  button: {
    padding: '10px 20px',
    borderRadius: '5px',
    border: 'none',
    cursor: 'pointer',
    fontSize: '16px',
    fontWeight: 'bold',
  },
  confirmButton: {
    background: '#012441',
    color: '#fff',
  },
  cancelButton: {
    background: '#dc3545',
    color: '#fff',
  },
};

export default Doctor;
