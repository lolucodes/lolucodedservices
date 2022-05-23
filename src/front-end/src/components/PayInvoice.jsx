import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
// import InvoiceService from "../../services/invoice.service";
import Invoice from "../models/Invoice";
import {Button, Form} from "react-bootstrap";
import InvoiceService from "../services/invoice.service";

const PayInvoice = () => {

    const [errorMessage, setErrorMessage] = useState('');
    const currentUser = useSelector(state => state.user);
    const [infoMessage, setInfoMessage] = useState('');

    const [reference, setReference ] = useState("")

    // useEffect(() => {
    //     InvoiceService.getAllInvoices().then((response) => {
    //         console.log(response.data, "response.data")
    //         setInvoiceList(response.data);
    //     })
    // }, []);
    //
    // const pay = (invoice) => {
    //     if (!currentUser?.id) {
    //         setErrorMessage('You should login to Clear Outstanding Invoice.');
    //         return;
    //     }
    //
    //     const pay = new Invoice(currentUser.id, invoice.status, invoice.type, invoice.amount, invoice.reference, invoice.dueDate);
    //
    //     InvoiceService.payInvoice(invoice).then(() => {
    //         setInfoMessage('Payment Successfully');
    //     }).catch((err) => {
    //         setErrorMessage('Unexpected error occurred.');
    //         console.log(err);
    //     });
    // };


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
            <Form onSubmit={(e) => {
                e.preventDefault()
                console.log(reference)

                InvoiceService.payInvoice(reference).then((response) => {
                    console.log(response, "Ii am being sent...")
                    setInfoMessage('Payment Successfully');
                }).catch((err) => {
                    setErrorMessage('Unexpected error occurred.');
                    console.log(err);
                });
            } } >
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Invoice Reference Number</Form.Label>
                    <Form.Control value={reference} onChange={(e) => setReference(e.target.value) } type="text" placeholder="Enter Invoice reference Number" />
                    <Form.Text className="text-muted">
                        Kindly Settle All Outstanding Invoices.
                    </Form.Text>
                </Form.Group>

                <Button variant="primary" type="submit">
                    Pay Invoice
                </Button>
            </Form>

        </div>
    )


}

export {PayInvoice}



