import {Button, Form} from "react-bootstrap";
import {useState} from "@types/react";
import {useDispatch, useSelector} from "react-redux";
import {Role} from "../../models/role";
import UserService from "../../services/user.service";
import {clearCurrentUser} from "../../store/actions/user";
import {useNavigate} from "react-router-dom";

const StudentProfilePage = () => {

    const [errorMessage, setErrorMessage] = useState('');
    const currentUser = useSelector(state => state.user);
    const [infoMessage, setInfoMessage] = useState('');

    const [username, setUsername] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();


    const changeRole = () => {
        const newRole = currentUser.role === Role.ADMIN ? Role.USER : Role.ADMIN;

        UserService.changeRole(newRole).then(() => {
            // clear session
            dispatch(clearCurrentUser());
            navigate('/login');
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
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="username" placeholder="Enter Username"/>
                    <Form.Text className="text-muted">
                        Please Enter your new Username
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Student ID</Form.Label>
                    <Form.Control type="studentId" placeholder="studentId"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out"/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>

        </div>
    )

}
export {StudentProfilePage};