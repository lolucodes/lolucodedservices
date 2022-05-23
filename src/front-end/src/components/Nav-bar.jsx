import {NavLink, useNavigate} from "react-router-dom";
import {clearCurrentUser} from "../store/actions/user";
import {Role} from "../models/role";
import {useDispatch, useSelector} from "react-redux";
import {NavDropdown, NavItem} from "react-bootstrap";


const NavBar = () => {

    const currentUser = useSelector(state => state.user);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const logout = () => {
        dispatch(clearCurrentUser());
        navigate('/login');
    };

    return (
        <nav
            className="navbar navbar-expand navbar-dark bg-dark">

            <div className="navbar-nav me-auto">
                {currentUser?.role === Role.ADMIN &&
                    <li className="nav-item">
                        <NavLink to="/admin" className="nav-link">
                            Admin
                        </NavLink>
                    </li>
                }
                <li className="nav-item">
                    <NavLink to="/home" className="nav-link">
                        Home
                    </NavLink>
                </li>

                <li className="nav-item">
                    <NavLink to="/courses" className="nav-link">
                        All Courses
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink to="/profile" className="nav-link">
                        Profile
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink to="/Graduation" className="nav-link">
                        Graduation
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink to="/Enrollments" className="nav-link">
                        Enrollments
                    </NavLink>
                </li>


                <NavDropdown title="Finance Portal" id="basic-nav-dropdown">
                    <NavLink to="/Invoices" className="nav-link">
                        <NavDropdown.Item as={NavItem} >
                            View Invoices
                        </NavDropdown.Item>
                    </NavLink>

                    <NavLink to="/Payment" className="nav-link">
                        <NavDropdown.Item as={NavItem}>
                            Pay Invoice By Reference
                        </NavDropdown.Item>
                    </NavLink>
                    <NavLink to="/Finance" className="nav-link">
                        <NavDropdown.Item as={NavItem}>
                            View Account
                        </NavDropdown.Item>
                    </NavLink>


                    {/*<NavDropdown.Item href="#Invoices">Pay Invoice By Reference</NavDropdown.Item>*/}
                </NavDropdown>

                <form className="relative" >
                    <input
                        className="border-2 border-gray-300 bg-white h-10 px-5 pr-16 rounded-full text-sm focus:outline-none"
                        type="search" name="searchString" aria-label="Search" placeholder="Search courses"/>
                    <button type="submit" className="absolute right-0 top-0 mt-3 mb-2 mr-5">
                    </button>
                </form>


            </div>

            {!currentUser ? <div className="navbar-nav ms-auto">
                <li className="nav-item">
                    <NavLink to="/register" className="nav-link">
                        Sign Up
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink to="/login" className="nav-link">
                        Sign In
                    </NavLink>
                </li>
            </div>:
                <div className="navbar-nav ms-auto">
                <li className="nav-item">
                <NavLink to="/profile" className="nav-link">
            {currentUser.name}
                </NavLink>
                </li>
                <li className="nav-item">
                <a href="#" className="nav-link" onClick={() => logout()}>
                Sign Out
                </a>
                </li>

                </div>

            }

        </nav>
    );
};

export {NavBar};