package com.tuyiiya.xmlcomposemigration


class MyFragment : Fragment(R.layout.fragment_my) {
    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AndroidView(
                    factory = {
                        TextView(it)
                    }
                ) { textView ->
                    textView.apply {
                        text = "This is an XML view in a composable"
                        setTextColor(Color.BLACK)
                        textSize = 20f
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}