package com.example.fake_insta.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fake_insta.MainActivity
import com.example.fake_insta.Post
import com.example.fake_insta.PostAdapter
import com.example.fake_insta.R
import com.parse.FindCallback
import com.parse.ParseException
import android.content.Intent
import com.parse.ParseQuery

// TODO: Rename parameter arguments, choose names that match

class FeedFragment : Fragment() {

    lateinit var postRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter

    lateinit var swipeContainer: SwipeRefreshLayout
    var allPosts: MutableList<Post> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        // Only ever call `setContentView` once right at the top
//        view.setContentView(R.layout.activity_main)
        // Lookup the swipe container view
//        swipeContainer = view?.findViewById(R.id.swipeContainer)!!
//        // Setup refresh listener which triggers new data loading
//
//        swipeContainer.setOnRefreshListener {
//            // Your code to refresh the list here.
//            // Make sure you call swipeContainer.setRefreshing(false)
//            // once the network request has completed successfully.
//            queryPosts()
//        }
//
//        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//            android.R.color.holo_green_light,
//            android.R.color.holo_orange_light,
//            android.R.color.holo_red_light);

        queryPosts()

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all the post objects in our server
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    //something went wrong
                    Log.e(MainActivity.TAG, "Error fetching posts")
                } else {
                    if(posts != null){
                        for (i in 1..5) {
                            Log.i(MainActivity.TAG, "Post: " + posts[i].getDescription() + " , username: " + posts[i].getUser()?.username)
                        }

//                        allPosts.addAll(-5, posts)
                        adapter.clear()
                        for(i in 0..19){
                            allPosts.add(posts[i])
                        }

                        adapter.notifyDataSetChanged()

//                        swipeContainer.setRefreshing(false)


                    }
                }
            }

        })
    }

    override fun onViewCreated(view: View, savedInstancesState: Bundle?){
        super.onViewCreated(view, savedInstancesState)

        postRecyclerView = view.findViewById(R.id.postRecyclerView)
        adapter = PostAdapter(requireContext(), allPosts as ArrayList<Post>)
        postRecyclerView.adapter = adapter

        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    companion object {
        const val TAG = "FeedFragment"
    }

}