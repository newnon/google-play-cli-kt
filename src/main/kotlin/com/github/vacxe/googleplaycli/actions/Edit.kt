package com.github.vacxe.googleplaycli.actions

import com.github.vacxe.googleplaycli.actions.model.DefaultModel
import com.github.vacxe.googleplaycli.actions.model.EditModel
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppEdit

interface Edit: BaseAction {
    fun editCreate(model: DefaultModel): String {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        val insert = edits.insert(model.packageName, null).execute()
        return insert.id
    }

    fun editCommit(model: EditModel): AppEdit {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        return edits.commit(model.packageName, model.editId).set("changesNotSentForReview", model.changesNotSentForReview).execute()
    }

    fun editValidate(model: EditModel): AppEdit {
        val edits: AndroidPublisher.Edits = androidPublisher.edits()
        return edits.validate(model.packageName, model.editId).execute()
    }
}
