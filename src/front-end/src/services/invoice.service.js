import {BASE_API_URL} from "../common/Constants";
import axios from "axios";
import {authHeader} from "./base.service";


const API_URL = BASE_API_URL + '/gateway/invoices';


class InvoiceService {
    createNewInvoice(invoice) {
        return axios.post(API_URL, invoice, {headers: authHeader()});
    }

    getAllInvoices() {
        return axios.get(API_URL, {headers: authHeader()});
    }

    getInvoiceByReference(invoice) {
        return axios.get(API_URL+ '/reference' + invoice.reference, {headers: authHeader()});
    }

    payInvoice(reference) {
        return axios({
                method: 'put',
                url: API_URL + '/' + reference + '/pay',
                headers: authHeader()
            }
        )
    }

    cancelInvoice(invoice) {
        return axios.delete(API_URL+ '/reference' + invoice.reference + '/cancel', {headers: authHeader()});
    }

}


export default new InvoiceService();
