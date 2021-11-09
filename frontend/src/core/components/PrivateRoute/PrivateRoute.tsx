import { isAllowedByRole, isAuthenticated } from "core/utils/requests";
import { Route, Redirect } from "react-router-dom";

type Props = {
    children: React.ReactNode;
    path: string;
    allowedRoles: string[];
}

const PrivateRoute = ({children, path, allowedRoles}: Props) => {

    return (
        <Route 
        path={path}
        render={({ location }) => {
            if(!isAuthenticated()) {
                return (<Redirect to={{pathname: "/", state: { from: location }}} />)
            } else if (isAuthenticated() && !isAllowedByRole(allowedRoles)){
                return (<Redirect to={{pathname: "/"}} />)
            }
            return children
        }}
        />);




}

export default PrivateRoute;