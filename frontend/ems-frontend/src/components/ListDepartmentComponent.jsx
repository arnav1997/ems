import React from "react";
import { useEffect, useState } from "react";
import { deleteDepartmentById, listDepartments } from "../services/DepartmentService";
import { useNavigate } from "react-router-dom";

const ListDepartmentComponent = () => {
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
  // const [departments, setDepartments] = useState([]);

  // useEffect(() => {
  //   fetchDepartments();
  // }, []);

  // const fetchDepartments = async () => {
  //   const res = await fetch("http://localhost:8080/api/departments/all");
  //   const data = await res.json();
  //   setDepartments(data);
  // };

  // Method 2 using axios library
  const [departments, setDepartments] = useState([]);
  useEffect(() => {
    getAllDepartments();
  }, []);

  const getAllDepartments = () => {
    listDepartments()
      .then((response) => {
        setDepartments(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const navigator = useNavigate();
  function addNewDepartment() {
    navigator("/add-department");
  }

  const updateDepartment = (id) => {
    navigator(`/update-department/${id}`);
  };

  const deleteDepartment = (id) => {
    deleteDepartmentById(id)
      .then((response) => {
        console.log(response.data);
        getAllDepartments();
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="container">
      <h2 className="text-center">Departments</h2>
      <button
        type="button"
        className="btn btn-primary"
        onClick={addNewDepartment}
      >
        Add Department
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <th>Department ID</th>
          <th>Department Name</th>
          <th>Department Description</th>
          <th>Actions</th>
        </thead>
        <tbody>
          {departments.map((department) => (
            <tr key={department.id}>
              <td>{department.id}</td>
              <td>{department.deptName}</td>
              <td>{department.deptDesc}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateDepartment(department.id)}
                >
                  Update
                </button>
                <button
                  style={{ marginLeft: "10px" }}
                  className="btn btn-danger"
                  onClick={() => deleteDepartment(department.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListDepartmentComponent;
