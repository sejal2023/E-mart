
// // ProfileDropdown.jsx
// import React from "react";
// import { Link } from "react-router-dom";

// const ProfileDropdown = () => {
//   return (
//     <div className="btn-group">
//       <button
//         type="button"
//         className="btn btn-secondary rounded-circle border me-3"
//         data-bs-toggle="dropdown"
//         aria-expanded="false"
//         aria-label="Profile"
//       >
//         <i className="bi bi-person-fill text-light"></i>
//       </button>
//       <ul className="dropdown-menu">
//         <li>
//           <Link className="dropdown-item" to="/account/profile">
//             <i className="bi bi-person-square"></i> My Profile
//           </Link>
//         </li>
//         <li>
//           <Link className="dropdown-item" to="/star/zone">
//             <i className="bi bi-star-fill text-warning"></i> Star Zone
//           </Link>
//         </li>
//         <li>
//           <Link className="dropdown-item" to="/account/orders">
//             <i className="bi bi-list-check text-primary"></i> Orders
//           </Link>
//         </li>
//         <li>
//           <Link className="dropdown-item" to="/account/wishlist">
//             <i className="bi bi-heart-fill text-danger"></i> Wishlist
//           </Link>
//         </li>
//         <li>
//           <hr className="dropdown-divider" />
//         </li>
//         {/* <li>
//           <Link className="dropdown-item" to="/account/notification">
//             <i className="bi bi-bell-fill text-primary"></i> Notification
//           </Link>
//         </li>
//         <li>
//           <Link className="dropdown-item" to="/support">
//             <i className="bi bi-info-circle-fill text-success"></i> Support
//           </Link>
//         </li>
//         <li>
//           <hr className="dropdown-divider" />
//         </li> */}
//         <li>
//           <Link className="dropdown-item" to="/">
//             <i className="bi bi-door-closed-fill text-danger"></i> Logout
//           </Link>
//         </li>
//       </ul>
//     </div>
//   );
// };

// export default ProfileDropdown;


import React from "react";
import { Dropdown } from "react-bootstrap";
import { Link } from "react-router-dom";

const ProfileDropdown = ({ onLogout }) => {
  return (
    <Dropdown>
      <Dropdown.Toggle
        variant="secondary"
        className="rounded-circle "
        id="dropdown-basic"
      >
        <i className="bi bi-person-fill text-light"></i>
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item as={Link} to="/account/profile">
          <i className="bi bi-person-square"></i> My Profile
        </Dropdown.Item>
        <Dropdown.Item as={Link} to="/star/zone">
          <i className="bi bi-star-fill text-warning"></i> Star Zone
        </Dropdown.Item>
        <Dropdown.Item as={Link} to="/account/orders">
          <i className="bi bi-list-check text-primary"></i> Orders
        </Dropdown.Item>
        <Dropdown.Item as={Link} to="/account/wishlist">
          <i className="bi bi-heart-fill text-danger"></i> Wishlist
        </Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item as={Link} to="/support">
          <i className="bi bi-info-circle-fill text-success"></i> Support
        </Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item onClick={onLogout}>
          <i className="bi bi-door-closed-fill text-danger"></i> Logout
        </Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
};

export default ProfileDropdown;
