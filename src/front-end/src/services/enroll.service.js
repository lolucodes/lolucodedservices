import { BASE_API_URL } from '../common/Constants'
import axios from "axios";
import {authHeader} from "./base.service";

const API_URL = BASE_API_URL + '/gateway/enroll';

class EnrollService {

    enrolInCourse(enrollment) {
        return axios.post(API_URL, enrollment, {headers: authHeader()});
    }

    getAllEnrolledCourses() {
        return axios.get(API_URL, {headers: authHeader()});
    }
}

export default new EnrollService();