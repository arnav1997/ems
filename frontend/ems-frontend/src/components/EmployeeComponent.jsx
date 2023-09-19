import React, { useEffect, useState } from 'react'
import { createEmployee, getEmployeeById, updateEmployeeById } from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router-dom';

const EmployeeComponent = () => {

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const navigator = useNavigate();
  const [errors, setErrors] = useState({firstName: "", lastName: "", email: ""});
  const {id} = useParams();
  useEffect(() => {
    if (id) {
      getEmployeeById(id).then(response => {
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
        setEmail(response.data.email);
      }).catch(error => {
        console.error(error);
      })
    }
  }, [id])

  const validateForm = () => {
    let valid = true
    const errorsCopy = {... errors}

    if (firstName.trim()) {
      errorsCopy.firstName = "";
    } else {
      valid = false;
      errorsCopy.firstName = "First Name can't be blank!";
    }

    if (lastName.trim()) {
      errorsCopy.lastName = "";
    } else {
      valid = false;
      errorsCopy.lastName = "Last Name can't be blank!";
    }

    if (email.trim()) {
      errorsCopy.email = "";
    } else {
      valid = false;
      errorsCopy.email = "Email ID can't be blank!";
    }

    setErrors(errorsCopy);
    return valid;
  }

  const saveEmployee = (e) => {
    e.preventDefault();

    const employee = {firstName, lastName, email};
    console.log(employee);

    if(validateForm()) {
      if (id) {
        updateEmployeeById(id, employee)
          .then((response) => {
            console.log(response);
            navigator("/employees");
          })
          .catch((error) => {
            console.log(error);
            alert("Error!");
          });
      } else {
        createEmployee(employee)
        .then(response => {
          console.log(response);
          navigator('/employees')
        })
        .catch(error => {
          console.log(error);
          alert("Error!")
        });
      }
    }
  }

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>
    } else {
      return <h2 className="text-center">Add Employee</h2>
    }
  }

  return (
    <div className="container">
      <br /> <br />
      <div className="row">
        <div className="card col-md-6 offset-md-3 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">First Name:</label>
                <input
                  type="text"
                  placeholder="Enter First Name"
                  name="firstName"
                  value={firstName}
                  className={`form-control ${
                    errors.firstName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setFirstName(e.target.value)}
                ></input>
                {errors.firstName && (
                  <div className="invalid-feedback"> {errors.firstName} </div>
                )}
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Last Name:</label>
                <input
                  type="text"
                  placeholder="Enter Last Name"
                  name="lastName"
                  value={lastName}
                  className={`form-control ${
                    errors.lastName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setLastName(e.target.value)}
                ></input>
                {errors.lastName && (
                  <div className="invalid-feedback"> {errors.lastName} </div>
                )}
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Email ID:</label>
                <input
                  type="text"
                  placeholder="Enter Email ID"
                  name="email"
                  value={email}
                  className={`form-control ${errors.email ? "is-invalid" : ""}`}
                  onChange={(e) => setEmail(e.target.value)}
                ></input>
                {errors.email && (
                  <div className="invalid-feedback"> {errors.email} </div>
                )}
              </div>
              <div className="text-center">
                <button className="btn btn-success" onClick={saveEmployee}>
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EmployeeComponent