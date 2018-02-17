package ru.fitnessmanager.to;

import ru.fitnessmanager.model.Parameter;
import ru.fitnessmanager.model.UserParameters;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserParametersTo {

    @NotNull
    private Parameter parameter;

    @NotNull
    private List<UserParameters> userParameters;

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public List<UserParameters> getUserParametersList() {
        return userParameters;
    }

    public void setUserParametersList(List<UserParameters> userParameters) {
        this.userParameters = userParameters;
    }

    public UserParametersTo(Parameter parameter, List<UserParameters> userParameters) {
        this.parameter = parameter;
        this.userParameters = userParameters;
    }
}
