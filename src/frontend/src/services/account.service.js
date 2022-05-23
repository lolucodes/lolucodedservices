import {BASE_API_URL} from "../common/Constants";
import axios from "axios";
import {authHeader} from "./base.service";


const API_URL = BASE_API_URL + '/gateway/accounts';

class AccountService {

    saveAccount(userId) {
        return axios.post(API_URL, { userId } , {headers: authHeader()});

    }

    getAccountStatus() {
        return axios.get(API_URL)
    }
}

export default new AccountService();