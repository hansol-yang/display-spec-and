package com.example.display_spec_and

class MainPresenter(private var view: MainContract.View?) :
    MainContract.Presenter {
    override fun onViewCreate() {
        view?.showScreenSize()
        view?.showScreenDensity()
        view?.showScaledDensity()
    }

    override fun onViewDestroy() {
        view = null
    }
}