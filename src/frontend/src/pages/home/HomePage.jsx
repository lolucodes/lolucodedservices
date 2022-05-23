import { useEffect, useState } from 'react';
import CourseService from '../../services/course.service';
import { useSelector } from 'react-redux';
import Enroll from '../../models/enroll';
import './HomePage.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faUserGraduate } from '@fortawesome/free-solid-svg-icons';
import EnrollService from "../../services/enroll.service";


const HomePage = () => {

    const currentUser = useSelector(state => state.user);



    return (
        <div className="container p-3">

            <div className=" py-12">
            <div className="max-w-7xl mx-auto px-4 sm:px-6">
                <div className="flex items-center  space-x-2">
                    <h2 className="text-3xl font-semibold" >Hello,
                    </h2>
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24"
                         stroke="currentColor" stroke-width="2">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                    </svg>
                </div>
                <div className="my-16 text-base w-1/2">
                    Welcome to your Student portal You can view all the courses offered,
                    Enrol in any of the courses if this is your first enrolment,
                    You can also see all courses you are enrolled in,
                    View and update your profile, lastly you can view your eligibility to graduate. Note that you must
                    not have
                    any outstanding invoices. Pay all outstanding invoices in order to graduate
                </div>
            </div>
        </div>


        </div>
    );
};

export {HomePage}