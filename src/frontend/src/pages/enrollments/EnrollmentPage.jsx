import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import EnrollService from '../../services/enroll.service'
import UserService from "../../services/user.service";
import { Role} from "../../models/role";
import {useNavigate} from "react-router-dom";
import {clearCurrentUser} from "../../store/actions/user";


const EnrollmentPage = () => {

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
                                <h2>Student Portal</h2>
                            </div>

                        </div>

                    </div>
                    <div className="card-body">
                        <table className="table table-striped">

                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Title</th>
                                <th scope="col">Price</th>
                                <th scope="col">Date Enrolled</th>

                            </tr>
                            </thead>
                            <tbody>

                            {enrollmentList.map((item, ind) =>
                                <tr key={item.id}>
                                    <th scope="row">{ind + 1}</th>
                                    <td>{item.title}</td>
                                    <td>{`$ ${item.price}`}</td>
                                    <td>{new Date(item.enrollmentTime).toLocaleDateString()}</td>
                                </tr>
                            )}


                            </tbody>

                        </table>
                    </div>
                </div>

            </div>
        </div>
    );
};

export {EnrollmentPage};
