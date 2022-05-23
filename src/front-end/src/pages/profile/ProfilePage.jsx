import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import EnrollService from '../../services/enroll.service'
import UserService from "../../services/user.service";
import { Role} from "../../models/role";
import {useNavigate} from "react-router-dom";
import {clearCurrentUser} from "../../store/actions/user";


const ProfilePage = () => {

    const [enrollmentList, setEnrollmentList] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');

    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        EnrollService.getAllEnrolledCourses().then((response) => {
            setEnrollmentList(response.data);
        });
    }, []);

    const changeRole = () => {
        const newRole = currentUser.role === Role.ADMIN ? Role.USER : Role.ADMIN;

        UserService.changeRole(newRole).then(() => {
            // clear session
            dispatch(clearCurrentUser());
            navigate('/login');
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });
    };

    return (
        <div className="container">
            <div className="pt-5">

                {errorMessage &&
                    <div className="alert alert-danger">
                        {errorMessage}
                    </div>
                }

                <div className="card">
                    <div className="card-header">

                        <div className="row">
                            <div className="col-6">
                            </div>
                            <div className="col-6 text-end">
                                Current role is <strong>{currentUser?.role} </strong>
                                <button className="btn btn-primary" onClick={() => changeRole()}>
                                    Change Role
                                </button>
                            </div>
                            <div>
                                <ul className=" py-20 mx-auto text-center text-xl space-y-4 border px-4">
                                    <li >Student Profile</li>
                                    <li >First Name: </li>
                                    <li >Surname: </li>
                                    <li >Student ID: </li>
                                </ul>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    );
};

export {ProfilePage};
