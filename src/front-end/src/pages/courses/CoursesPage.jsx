import { useEffect, useState } from 'react';
import CourseService from '../../services/course.service';
import {useDispatch, useSelector} from 'react-redux';
import Enroll from '../../models/enroll';
import './CoursesPage.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faUserGraduate } from '@fortawesome/free-solid-svg-icons';
import EnrollService from "../../services/enroll.service";
import Invoice from "../../models/Invoice";
import InvoiceService from "../../services/invoice.service";
import AccountService from "../../services/account.service";
import {Type} from "../../models/Type";
import {clearCurrentUser} from "../../store/actions/user";
import {useNavigate} from "react-router-dom";


const CoursesPage = () => {

    const [courseList, setCourseList] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const [infoMessage, setInfoMessage] = useState('');

    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        CourseService.getAllCourses().then((response) => {
            setCourseList(response.data);
        });
    }, []);

    const enroll =async(course) => {
        if (!currentUser?.id) {
            setErrorMessage('You should login to enroll in a course.');
            return;
        }

        const enroll = new Enroll(currentUser.id, course.id, course.title, course.price);

        EnrollService.enrolInCourse(enroll).then(() => {
            setInfoMessage('Enrolled Successfully');

        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });

        // const newAccount =await AccountService.saveAccount(currentUser?.id)
        const newInvoice = new Invoice(currentUser?.id,course.price,Type.TUITION_FEES)


        InvoiceService.createNewInvoice(newInvoice).then((x) => {
            console.log(x)
            setInfoMessage('Invoice Created Successfully');

        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });
    };

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
                {courseList.map((item, ind) =>
                    <div key={item.id} className="card m-3 home-card">

                        <div className="card-body">
                            <div className="card-title text-uppercase">
                                {item.title}
                            </div>
                            <div className="card-subtitle text-muted">
                                {item.subtitle}
                            </div>
                        </div>

                        <FontAwesomeIcon icon={faUserGraduate} className="ms-auto me-auto course-icon"/>

                        <div className="row mt-2 p-3">
                            <div className="col-6 mt-2 ps-4">
                                {`$ ${item.price}`}
                            </div>
                            <div className="col-6">
                                <button
                                    className="btn btn-outline-success w-100"
                                    onClick={() => enroll(item)}>
                                    Enroll
                                </button>
                            </div>
                        </div>

                    </div>
                )}
            </div>
        </div>
    );
};

export {CoursesPage}
