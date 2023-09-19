import React, { useEffect, useState } from "react";
import {
  createDepartment,
  getDepartmentById,
  updateDepartmentById,
} from "../services/DepartmentService";
import { useNavigate, useParams } from "react-router-dom";

const DepartmentComponent = () => {
  const [deptName, setDeptName] = useState("");
  const [deptDesc, setDeptDesc] = useState("");
  const navigator = useNavigate();
  const [errors, setErrors] = useState({
    deptName: "",
    deptDesc: ""
  });
  const { id } = useParams();
  useEffect(() => {
    if (id) {
      getDepartmentById(id)
        .then((response) => {
          setDeptName(response.data.deptName);
          setDeptDesc(response.data.deptDesc);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [id]);

  const validateForm = () => {
    let valid = true;
    const errorsCopy = { ...errors };

    if (deptName.trim()) {
      errorsCopy.deptName = "";
    } else {
      valid = false;
      errorsCopy.deptName = "First Name can't be blank!";
    }

    if (deptDesc.trim()) {
      errorsCopy.deptDesc = "";
    } else {
      valid = false;
      errorsCopy.deptDesc = "Last Name can't be blank!";
    }

    setErrors(errorsCopy);
    return valid;
  };

  const saveDepartment = (e) => {
    e.preventDefault();

    const department = { deptName, deptDesc };
    console.log(department);

    if (validateForm()) {
      if (id) {
        updateDepartmentById(id, department)
          .then((response) => {
            console.log(response);
            navigator("/departments");
          })
          .catch((error) => {
            console.log(error);
            alert("Error!");
          });
      } else {
        createDepartment(department)
          .then((response) => {
            console.log(response);
            navigator("/departments");
          })
          .catch((error) => {
            console.log(error);
            alert("Error!");
          });
      }
    }
  };

  const pageTitle = () => {
    if (id) {
      return <h2 className="text-center">Update Department</h2>;
    } else {
      return <h2 className="text-center">Add Department</h2>;
    }
  };

  return (
    <div className="container">
      <br /> <br />
      <div className="row">
        <div className="card col-md-6 offset-md-3 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">Department Name:</label>
                <input
                  type="text"
                  placeholder="Enter Department Name"
                  name="deptName"
                  value={deptName}
                  className={`form-control ${
                    errors.deptName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setDeptName(e.target.value)}
                ></input>
                {errors.deptName && (
                  <div className="invalid-feedback"> {errors.deptName} </div>
                )}
              </div>
              <div className="form-group mb-2">
                <label className="form-label">Department Description:</label>
                <input
                  type="text"
                  placeholder="Enter Department Description"
                  name="deptDesc"
                  value={deptDesc}
                  className={`form-control ${
                    errors.deptDesc ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setDeptDesc(e.target.value)}
                ></input>
                {errors.deptDesc && (
                  <div className="invalid-feedback"> {errors.deptDesc} </div>
                )}
              </div>
              <div className="text-center">
                <button className="btn btn-success" onClick={saveDepartment}>
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DepartmentComponent;
