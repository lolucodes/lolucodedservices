import {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import AccountService from "../../services/account.service";
import {Spinner} from "react-bootstrap";

const GraduationPage = () => {

    const [graduationStatus, setGraduationStatus] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const [isLoading, setIsLoading ] = useState(false)

    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        setIsLoading(true)
        AccountService.getAccountStatus().then((response) => {
            console.log(response, "this is the graduation response")
            setGraduationStatus(response.data)
            setIsLoading(false)
        }).catch(err => {
            console.log(err, "An error occured!!!")
            setIsLoading(false)

        });
    },[]);

    console.log(graduationStatus, "__graduation__status__s")
    return (

        <div className="max-w-7xl mx-auto px-4 sm:px-6 py-12 space-y-2">


            <div className="bg-gray-200 py-2 px-4 text-sm">Important: Before you can graduate You need to pay all
                outstanding Tuition and library fees Thank you
            </div>
            <div >Please Settle All outstanding balance through The finance portal.</div>
            <div className="card-body">
                <table className="table table-striped">

                    <thead>
                    <tr>
                        <th scope="col">Student Id</th>
                        <th scope="col">Graduation Status</th>

                    </tr>
                    </thead>
                    <tbody>


                    { isLoading ? (
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    ) :  <tr>
                        <td>{graduationStatus?.studentId}</td>
                        <td>{` ${graduationStatus.hasOutstandingBalance ? "You are eligible": "You are ineligible To Graduate"}`}</td>
                    </tr>
                    }


                    </tbody>

                </table>
            </div>
        </div>

    );
};

export {GraduationPage}