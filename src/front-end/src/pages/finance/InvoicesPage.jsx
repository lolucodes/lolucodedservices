import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import InvoiceService from "../../services/invoice.service";
import Enroll from "../../models/enroll";
import EnrollService from "../../services/enroll.service";
import Invoice from "../../models/Invoice";

const InvoicesPage = () => {

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

        InvoiceService.payInvoice(invoice).then(() => {
            setInfoMessage('Payment Successfully');
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });
    };


    return(
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
                </div>

                <div className="card-body">
                    <table className="table table-striped">

                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Status</th>
                            <th scope="col">Type</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Reference</th>
                            <th scope="col">Due Date </th>

                        </tr>
                        </thead>
                        <tbody>

                        {invoiceList.map((item, ind) =>
                            <tr key={item.id}>
                                <th scope="row">{ind + 1}</th>
                                <td>{item.status}</td>
                                <td>{` ${item.type}`}</td>
                                <td>{`$ ${item.amount}`}</td>
                                <td>{` ${item.reference}`}</td>
                                <td>{new Date(item.dueDate).toLocaleDateString()}</td>

                            </tr>
                        )}


                        </tbody>

                    </table>
                </div>


            </div>
                </div>

    )

}

export {InvoicesPage}