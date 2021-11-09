import PrivateRoute from "core/components/PrivateRoute/PrivateRoute"
import { newHistory } from "core/utils/requests"
import Auth from "pages/Auth/Auth"
import Profile from "pages/Profile/Profile"
import { Router, Route, Switch } from "react-router-dom"

const Routes = () => (
    <Router history={newHistory}> 
        <Switch>
            <Route path="/" exact>
                <Auth/>
            </Route>
            <PrivateRoute path="/player" allowedRoles={["JOGADOR","MASTER"]}>
                <Profile/>
            </PrivateRoute>
            <PrivateRoute path="/master" allowedRoles={["MASTER"]}>
                <h1>Master</h1>
            </PrivateRoute>
        </Switch>
    </Router>)

export default Routes