import axios from "axios";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashAlt } from "@fortawesome/free-solid-svg-icons";
import "./AdminPageComponent.css";
function AdminPageComponent() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // sportify-first\src\sample-data\user-list-data.json
    // https://github.com/project-sportify-org/sportify-grand-reopening/blob/a144343c5b1fcfdb286edd4c1c0c23a82486e312/sportify-first/src/sample-data/user-list-data.json
    axios
      .get("/sample-data/user-list-data.json")
      .then((response) => setUsers(response.data.users))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="admin-home-container">
      <div className="admin-page-heading">
        <h2>Sportify User Lists</h2>
      </div>
      <div className="user-cards-list">
        {users.map((user) => (
          <div key={user.id} className="user-profile">
            <div className="user-desc">
              <h4>{user.name}</h4>
              <h5>{user.id}</h5>
              <p>{user.city}</p>
            </div>
            <div className="delete-icon">
              <FontAwesomeIcon icon={faTrashAlt} />;
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminPageComponent;
