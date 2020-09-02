package com.vazheninapps.testemployees.screens.employee;

import com.vazheninapps.testemployees.api.ApiFactory;
import com.vazheninapps.testemployees.api.ApiService;
import com.vazheninapps.testemployees.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private Disposable disposable;
    private EmployeeListView view;

    public EmployeeListPresenter(EmployeeListView view) {
        this.view = view;
    }

    public void loadData(){

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.showData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       view.showError(throwable.getMessage());
                    }
                });
    }

    public void disposeDisposable(){
        if(disposable!=null){
        disposable.dispose();}
    }
}
