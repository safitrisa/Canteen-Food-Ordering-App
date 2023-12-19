import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuMakanActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuItemList: List<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_makan)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.userList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi data dummy (gantilah dengan data sesuai kebutuhan Anda)
        menuItemList = listOf(
            MenuItem("Nama Item 1", "Deskripsi Item 1"),
            MenuItem("Nama Item 2", "Deskripsi Item 2"),
            MenuItem("Nama Item 3", "Deskripsi Item 3")
        )

        // Inisialisasi adapter dan menghubungkannya dengan RecyclerView
        menuAdapter = MenuAdapter(menuItemList)
        recyclerView.adapter = menuAdapter
    }
}
