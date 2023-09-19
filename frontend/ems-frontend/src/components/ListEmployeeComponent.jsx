import React from 'react'
import { useEffect, useState } from "react";
import { deleteEmployeeById, listEmployees } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';

const ListEmployeeComponent = () => {
  // Dummy data
  // const empData = [{
  //     "id" : 1,
  //     "firstName" : "Arnav",
  //     "lastName" : "Mahapatra",
  //     "email" : "arnav.mahapatra31@gmail.com"
  // },
  // {
  //     "id" : 2,
  //     "firstName" : "Jenny",
  //     "lastName" : "Mahapatra",
  //     "email" : "jenny.mahapatra31@gmail.com"
  // },
  // {
  //     "id" : 3,
  //     "firstName" : "Moon",
  //     "lastName" : "Mahapatra",
  //     "email" : "moon.mahapatra31@gmail.com"
  // }];

  // Method 1 using fetch()
  // const [employees, setEmployees] = useState([]);

  // useEffect(() => {
  //   fetchEmployees();
  // }, []);

  // const fetchEmployees = async () => {
  //   const res = await fetch("http://localhost:8080/api/employees/all");
  //   const data = await res.json();
  //   setEmployees(data);
  // };

  // Method 2 using axios library
  const [employees, setEmployees] = useState([]);
  useEffect(() => {
    getAllEmployees();
  }, [])

  const getAllEmployees = () => {
    listEmployees()
      .then((response) => {
        setEmployees(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  const navigator = useNavigate();
  function addNewEmployee() {
    navigator('/add-employee')
  }

  const updateEmployee = (id) => {
    navigator(`/update-employee/${id}`)
  }

  const deleteEmployee = (id) => {
    deleteEmployeeById(id)
      .then((response) => {
        console.log(response.data);
        getAllEmployees();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  return (
    <div className="container">
      <h2 className="text-center">Employees</h2>
      <button type="button" className="btn btn-primary" onClick={addNewEmployee}>Add Employee</button>
      <table className="table table-striped table-bordered">
        <thead>
          <th>Employee ID</th>
          <th>Employee First Name</th>
          <th>Employee Last Name</th>
          <th>Employee Email</th>
          <th>Actions</th>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}>Update</button>
                <button style={{marginLeft: '10px'}} className='btn btn-danger' onClick={() => deleteEmployee(employee.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ListEmployeeComponent