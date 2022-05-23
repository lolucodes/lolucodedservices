import {BASE_API_URL} from "../common/Constants";
import axios from "axios";
import {authHeader} from "./base.service";


const API_URL = BASE_API_URL + '/gateway/accounts';

class AccountService {

    saveAccount(userId) {
        return axios.post(API_URL, { userId } , {headers: authHeader()});

    }

    getAccountStatus() {
        // return axios.get("http://localhost:6689/gateway/accounts", { headers: authHeader() }

        const config = {
            method: 'get',
            url: 'http://localhost:6689/gateway/accounts',
            headers: authHeader()
            //     {
            //     'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYXJhdCIsInJvbGVzIjoiUk9MRV9TVFVERU5UIiwidXNlcklkIjoxMiwiZXhwIjoxNjUyOTEyMjczfQ.aIj1rhH3QOGTW9EsCXtSdZiBT0KYy8j6tW50wqfO72nhuh2N0tNPBqavYINQ0U0tTea_pHU1tGpWGOSFrD6INw',
            //     // 'Cookie': 'JSESSIONID=239DC510AB27C5EF8F17B6F94AB54C6D'
            // }
        };

        return axios(config)

    }

}

export default new AccountService();