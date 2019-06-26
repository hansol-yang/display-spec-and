package com.example.display_spec_and

class MainPresenter(private var view: MainContract.View?) :
    MainContract.Presenter {
    override fun onViewCreate() {
        view?.showScreenSize()
    }

    override fun onViewDestroy() {
        view = null
    }
}