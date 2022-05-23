import {useState} from "@types/react";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import AccountService from "../../services/account.service";

const GraduationPage = () => {

    const [graduationStatus, setGraduationStatus] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');


    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        AccountService.getAccountStatus().then((response) => {
            setGraduationStatus(response.data)
        });
    },[]);

    return (

        <div className="max-w-7xl mx-auto px-4 sm:px-6 py-12 space-y-2">

            <div>Graduation Status: </div>

            <div className="bg-gray-200 py-2 px-4 text-sm">Important: Before you can graduate You need to pay all
                outstanding Tuition and library fees Thank you
            </div>
            <div >Pay your outstanding balance through The finance portal by copying the Reference number of your enrolled course.</div>

        </div>

    );
};

export {GraduationPage}