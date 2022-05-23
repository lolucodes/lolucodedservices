import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import InvoiceService from "../../services/invoice.service";
import Invoice from "../../models/Invoice";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserGraduate} from "@fortawesome/free-solid-svg-icons";

const FinancePage = () => {

    const [invoiceList, setInvoiceList] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const currentUser = useSelector(state => state.user);
    const [infoMessage, setInfoMessage] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();



    useEffect(() => {
        InvoiceService.getAllInvoices().then((response) => {
            console.log(response.data, "response.data")
            setInvoiceList(response.data);
        })
    }, []);

    const pay = (invoice) => {
        if (!currentUser?.id) {
            setErrorMessage('You should login to Clear Outstanding Invoice.');
            return;
        }

        const pay = new Invoice(currentUser.id, invoice.status, invoice.type, invoice.amount, invoice.reference, invoice.dueDate);

        InvoiceService.payInvoice(pay).then(() => {
            setInfoMessage('Payment Successfully');
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });
    };


    return(
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
                {invoiceList.map((item, ind) =>
                    <div key={item.id} className="card m-3 home-card">

                        <div className="card-body">
                            <div className="card-title text-uppercase">
                                {item.status}
                            </div>
                            <div className="card-subtitle text-muted">
                                {item.reference}
                            </div>
                            <div className="card-subtitle text-muted">
                                {item.dueDate}
                            </div>
                            <div className="card-subtitle text-muted">
                                {item.type}
                            </div>
                        </div>

                        <FontAwesomeIcon icon={faUserGraduate} className="ms-auto me-auto course-icon"/>

                        <div className="row mt-2 p-3">
                            <div className="col-6 mt-2 ps-4">
                                {`$ ${item.amount}`}
                            </div>
                            <div className="col-6">
                                {/*<button*/}
                                {/*    className="btn btn-outline-success w-100"*/}
                                {/*    onClick={() => pay(item)}>*/}
                                {/*    Pay*/}
                                {/*</button>*/}
                            </div>
                        </div>

                    </div>
                )}
            </div>


        </div>

    )

}

export {FinancePage}