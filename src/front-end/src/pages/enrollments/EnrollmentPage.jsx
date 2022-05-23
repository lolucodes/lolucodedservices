import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import EnrollService from '../../services/enroll.service'
import UserService from "../../services/user.service";
import { Role} from "../../models/role";
import {useNavigate} from "react-router-dom";
import {clearCurrentUser} from "../../store/actions/user";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserGraduate} from "@fortawesome/free-solid-svg-icons";


const EnrollmentPage = () => {

    const [enrollmentList, setEnrollmentList] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const [infoMessage, setInfoMessage] = useState('');


    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        EnrollService.getAllEnrolledCourses().then((response) => {
            setEnrollmentList(response.data);
        });
    }, []);



    return (
        <div className="container p-3">
            {errorMessage &&
                <div className="alert alert-danger">
                    {errorMessage}
                </div>
            }

            {infoMessage &&
                <div className="alert alert-success">
                    {infoMessage}
                </div>
            }

            <div className="d-flex flex-wrap">
                {enrollmentList.map((item, ind) =>
                    <div key={item.id} className="card m-3 home-card">

                        <div className="card-body">
                            <div className="card-title text-uppercase">
                                {item.title}
                            </div>
                            <div className="card-subtitle text-muted">
                                {new Date(item.enrollmentTime).toLocaleDateString()}
                            </div>
                        </div>

                        <FontAwesomeIcon icon={faUserGraduate} className="ms-auto me-auto course-icon"/>

                        <div className="row mt-2 p-3">
                            <div className="col-6 mt-2 ps-4">
                                {`$ ${item.price}`}
                            </div>
                            <div className="col-6">

                            </div>
                        </div>

                    </div>
                )}
            </div>
        </div>
    );
};

export {EnrollmentPage};
