package com.daniily.kodeprojectfive

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.daniily.kodeprojectfive.view.FeedAbsorber
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {
        hideFragments()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        hideFragments()
        when (position) {
            0 -> fragment_add_news.visibility = View.VISIBLE
            1 -> fragment_add_notifications.visibility = View.VISIBLE
        }
    }

    lateinit var adapter: ArrayAdapter<FeedAbsorber>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, FeedAbsorber.values())
        new_note_spinner.adapter = adapter
        new_note_spinner.onItemSelectedListener = this

        action_add_note.setOnClickListener(this::addClick)
    }

    private fun hideFragments() {
        fragment_add_news.visibility = View.GONE
        fragment_add_notifications.visibility = View.GONE
    }

    private fun addClick(v: View) {
        when (new_note_spinner.selectedItemPosition) {
            0 ->
                appDAO.saveFeedObjects(
                    appDAO.getFeedObjects().apply { add(FeedAbsorber.NEWS_ABSORBER.absorb(fragment_add_news)) }
                )

            1 ->
                appDAO.saveFeedObjects(
                    appDAO.getFeedObjects().apply {
                        add(FeedAbsorber.NOTIFICATION_ABSORBER.absorb(fragment_add_notifications))
                    }
                )
        }
        finish()
    }
}
